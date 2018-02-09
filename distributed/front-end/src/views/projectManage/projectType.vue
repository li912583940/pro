<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="名称"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="search" icon="el-icon-search">搜索</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd" icon="el-icon-plus">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="projectTypes" size="small" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;" height="500" border>
			<el-table-column type="selection" width="60" fixed="left">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="id" label="id主键" width="120" sortable>
			</el-table-column>
			<el-table-column prop="name" label="项目类型名称"  sortable>
			</el-table-column>
			<el-table-column label="操作" width="200" fixed="right"> 
				<template scope="scope">
					<el-button type="info" size="small" @click="handleEdit(scope.$index, scope.row)" icon="el-icon-edit">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0" icon="el-icon-delete">批量删除</el-button>
			<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
				:page-sizes="[10, 20, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" prev-text="上一页" next-text="下一页"  :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑用户-->
		<el-dialog title="编辑项目类别" :visible.sync="editVisible">
		    <el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm">
		    	<el-form-item label="项目类别名称" prop="name">
					<el-input  v-model="editForm.name" ></el-input>
				</el-form-item>
			</el-form>
		  	<div slot="footer" class="dialog-footer">
		    	<el-button @click="editVisible = false">取 消</el-button>
		    	<el-button type="primary" @click="editSubmit" :loading="editLoading">提交</el-button>
		  	</div>
		</el-dialog>
		<!--新增界面-->
		<el-dialog title="新增项目类别" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="120px" :rules="addFormRules" ref="addForm">
				<el-form-item label="项目类别名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off" clearable ></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import { getProjectTypeListPage, removeProjectType, batchRemoveProjectType, editProjectType, addProjectType, getProjectTypeOne } from '@/api/projectTypeApi';

	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				projectTypes: [],
				total: 0,
				pageNum: 1,//当前页码
				pageSize: 20,
				listLoading: false,
				sels: [],//列表选中列

				editVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入名称', trigger: 'blur' }
					]
				},
				//编辑用户界面数据
				editForm: {
					id: 0,
					name: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入名称', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					name: '',
				}
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.pageNum = val;//页码
				this.geProjectTypes();//翻页操作
			},
			handleSizeChange(val) {
				this.pageSize = val;//设置每页显示多少条数据
		        this.search();//重新查询
		    },
		    search() {//重新查询，并从第一页开始
		    	this.pageNum = 1;
		    	this.getProjectTypes();
		    },
			//获取用户列表
			getProjectTypes() {
				let para = {
					pageNum: this.pageNum,
					pageSize: this.pageSize,
					name: this.filters.name
				};
				this.listLoading = true;
				//NProgress.start();
				getProjectTypeListPage(para).then((res) => {
					this.total = res.count;
					this.projectTypes = res.data;
					this.listLoading = false;
					//NProgress.done();
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: row.id };
					removeProjectType(para).then((res) => {
						this.listLoading = false;
						if (res.state === 'success') {
							this.$message({
								message: res.message,
								type: 'success'
							});
						} else {
							this.$message({
								message: res.message,
								type: 'error'
							});
						}
						this.getProjectTypes();
					});
				}).catch(() => {

				});
			},
				//显示编辑界面
			handleEdit: function (index, row) {
				this.resetForm('editForm');//打开页面之前先重置表单
				this.editVisible = true;
				let para = {
					id: row.id
				}
				getProjectTypeOne(para).then((res) => {
					this.editForm.id = res.data.id;
					this.editForm.name = res.data.name;
				});
			},
			//显示新增界面
			handleAdd: function () {
				this.resetForm('addForm');
				this.addFormVisible = true;
			},
			//重置表单
			resetForm: function(formName) {
				if(this.$refs[formName] !== undefined){
					this.$refs[formName].resetFields();
				}
		     },
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							editProjectType(para).then((res) => {
								this.editLoading = false;
								if (res.state === 'success') {
									this.$message({
										message: res.message,
										type: 'success'
									});
								} else {
									this.$message({
										message: res.message,
										type: 'error'
									});
								}
								this.resetForm('editForm');
								this.editVisible = false;
								this.getProjectTypes();
							}).catch((res) => {
								this.editLoading = false;
							});
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForm);
							addProjectType(para).then((res) => {
								this.addLoading = false;
								if (res.state === 'success') {
									this.$message({
										message: res.message,
										type: 'success'
									});
								} else {
									this.$message({
										message: res.message,
										type: 'error'
									});
								}
								this.resetForm('addForm');
								this.addFormVisible = false;
								this.getProjectTypes();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					console.log(para);
					batchRemoveProjectType(para).then((res) => {
						this.listLoading = false;
						if (res.state === 'success') {
							this.$message({
								message: res.message,
								type: 'success'
							});
						} else {
							this.$message({
								message: res.message,
								type: 'error'
							});
						}
						this.getProjectTypes();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
			this.getProjectTypes();
		}
	}

</script>

<style scoped>

</style>