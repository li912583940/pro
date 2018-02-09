<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="姓名"></el-input>
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
		<el-table :data="users" size="small" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;" height="500" border>
			<el-table-column type="selection" width="60" fixed="left">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="username" label="用户名" width="120" sortable>
			</el-table-column>
			<el-table-column prop="name" label="姓名" width="120" sortable>
			</el-table-column>
			<!--<el-table-column prop="sex" label="性别" width="100" :formatter="formatSex" sortable>
			</el-table-column>-->
			<el-table-column prop="createTime" label="创建时间" width="140" :formatter="dateFormat" sortable>
			</el-table-column>
			<el-table-column prop="updateTime" label="修改时间" width="140" :formatter="dateFormat" sortable>
			</el-table-column>
			<el-table-column prop="telephone" label="联系电话" min-width="120" sortable>
			</el-table-column>
			<el-table-column prop="mail" label="邮箱" min-width="180" sortable>
			</el-table-column>
			<el-table-column prop="lastSignTime" label="上次登录时间" min-width="140" :formatter="dateFormat" sortable>
			</el-table-column>
			<el-table-column prop="signCount" label="登录次数" min-width="180" sortable>
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
		<el-dialog title="编辑用户" :visible.sync="editVisible">
		    <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
		    	<el-form-item label="用户名" prop="username">
					<el-input  v-model="editForm.username" ></el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="editForm.name" auto-complete="off" clearable ></el-input>
				</el-form-item>
				<el-form-item label="联系电话" prop="telephone">
					<el-input v-model="editForm.telephone"  clearable ></el-input>
				</el-form-item>
				<el-form-item label="邮箱" prop="mail">
					<el-input  v-model="editForm.mail"  clearable></el-input>
				</el-form-item>
				<!--<el-form-item label="修改日期" prop="updateTime" >
					<el-date-picker
				      v-model="editForm.updateTime"
				      type="date"
				      placeholder="选择日期时间" 
				      value-format="yyyy-MM-dd HH:mm:ss"  clearable >
				    </el-date-picker>
				</el-form-item>-->
			</el-form>
		  	<div slot="footer" class="dialog-footer">
		    	<el-button @click="editVisible = false">取 消</el-button>
		    	<el-button type="primary" @click="editSubmit" :loading="editLoading">提交</el-button>
		  	</div>
		</el-dialog>
		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="用户名" prop="username">
					<el-input  v-model="addForm.username" ></el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="addForm.name" auto-complete="off" clearable ></el-input>
				</el-form-item>
				<el-form-item label="联系电话" prop="telephone">
					<el-input v-model="addForm.telephone"  clearable ></el-input>
				</el-form-item>
				<el-form-item label="邮箱" prop="mail">
					<el-input  v-model="addForm.mail"  clearable></el-input>
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
	import moment from 'moment';
	//import NProgress from 'nprogress'
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser, getUserOne } from '@/api/userApi';

	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				users: [],
				total: 0,
				pageNum: 1,//当前页码
				pageSize: 20,
				listLoading: false,
				sels: [],//列表选中列

				editVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑用户界面数据
				editForm: {
					id: 0,
					username: '',
					name: '',
					telephone: '',
					mail: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					username: '',
					name: '',
					telephone: '',
					mail: ''
				}

			}
		},
		methods: {
			//性别显示转换
			formatSex: function (row, column) {
				return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
			},
			dateFormat: function (row, column) {
				//时间格式化  
		        let date = row[column.property];  
		        if (date == undefined) {  
		          return "";  
		        }  
		        return moment(date).format("YYYY-MM-DD HH:mm:ss");  
			},
			dateFormats: function (val) {
				 return moment(val).format("YYYY-MM-DD HH:mm:ss");
			},
			handleCurrentChange(val) {
				this.pageNum = val;//页码
				this.getUsers();//翻页操作
			},
			handleSizeChange(val) {
				this.pageSize = val;//设置每页显示多少条数据
		        this.search();//重新查询
		    },
		    search() {//重新查询，并从第一页开始
		    	this.pageNum = 1;
		    	this.getUsers();
		    },
			//获取用户列表
			getUsers() {
				let para = {
					pageNum: this.pageNum,
					pageSize: this.pageSize,
					name: this.filters.name
				};
				console.log(para);
				this.listLoading = true;
				//NProgress.start();
				getUserListPage(para).then((res) => {
					this.total = res.count;
					this.users = res.data;
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
					removeUser(para).then((res) => {
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
						this.getUsers();
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
				getUserOne(para).then((res) => {
					if (res.data.updateTime != null) {
						res.data.updateTime = this.dateFormats(res.data.updateTime);
					}
					this.editForm.id = res.data.id;
					this.editForm.username = res.data.username;
					this.editForm.name = res.data.name;
					this.editForm.telephone = res.data.telephone;
					this.editForm.mail = res.data.mail;
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
							editUser(para).then((res) => {
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
								this.getUsers();
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
							addUser(para).then((res) => {
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
								this.getUsers();
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
					batchRemoveUser(para).then((res) => {
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
						this.getUsers();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
			this.getUsers();
		}
	}

</script>

<style scoped>

</style>