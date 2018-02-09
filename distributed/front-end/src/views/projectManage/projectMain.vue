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
		<el-table :data="projects" stripe size="small" highlight-current-row v-loading="listLoading" @selection-change="selsChange" @row-dblclick="handleFileDetails" style="width: 100%;" height="500"  border>
			<el-table-column type="selection" width="60" fixed="left">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="name" label="项目名" width="120" sortable>
			</el-table-column>
			<el-table-column prop="projectTypeName" label="项目类别" width="120" sortable>
			</el-table-column>
			<el-table-column prop="customerName" label="客户" width="120" sortable>
			</el-table-column>
			<el-table-column prop="lineInfo" label="线路信息" width="120" sortable>
			</el-table-column>
			<el-table-column prop="hardwareInfo" label="硬件信息" width="120" sortable>
			</el-table-column>
			<el-table-column prop="softwareInfo" label="软件信息" width="120" sortable>
			</el-table-column>
			<el-table-column prop="address" label="项目实施地址" width="140" sortable>
			</el-table-column>
			<el-table-column prop="province" label="省" width="120" sortable>
			</el-table-column>
			<el-table-column prop="city" label="市" width="120" sortable>
			</el-table-column>
			<el-table-column prop="area" label="区" width="120" sortable>
			</el-table-column>
			<el-table-column prop="implementTime" label="实施时间" width="100" :formatter="dateFormatSimp" sortable>
			</el-table-column>
			<el-table-column prop="implementUserName" label="实施人姓名" width="120" sortable>
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" width="140" :formatter="dateFormat" sortable>
			</el-table-column>
			<el-table-column prop="createUserName" label="创建人姓名" min-width="180" sortable>
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
				:page-sizes="[10, 20, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" prev-text="上一页" next-text="下一页" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑用户-->
		<el-dialog title="编辑用户" :visible.sync="editVisible">
		    <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="项目名称" prop="name">
					<el-input v-model="editForm.name" auto-complete="off" clearable ></el-input>
				</el-form-item>
				<el-form-item label="项目类别" prop="projectTypeName">
					<el-autocomplete class="inline-input" v-model="editForm.projectTypeName" :fetch-suggestions="projectTypeSearch"
				      placeholder="请输入项目类别" @select="projectTypeEditSelect" size="80" clearable></el-autocomplete>
				</el-form-item>
				<el-form-item label="客户" prop="customerName">
					<el-autocomplete class="inline-input" v-model="editForm.customerName" :fetch-suggestions="customerSearch"
				      placeholder="请输入客户姓名" @select="customerEditSelect" size="80" clearable></el-autocomplete>
				</el-form-item>
				<el-form-item label="线路信息" prop="lineInfo">
					<el-input  v-model="editForm.lineInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="硬件信息" prop="hardwareInfo">
					<el-input  v-model="editForm.hardwareInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="软件信息" prop="softwareInfo">
					<el-input  v-model="editForm.softwareInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="项目实施地址" prop="address">
					<el-input  v-model="editForm.address"  clearable></el-input>
				</el-form-item>
				<el-form-item label="选择地区" >
					<el-row :gutter="1">
					<el-col :span="20"><v-distpicker :province="editForm.province" :city="editForm.city" :area="editForm.area" 
						@province="provinceEdit" @city="cityEdit" @area="areaEdit" ></v-distpicker></el-col>
					<el-col :span="3"><el-button type="warning" @click="resetRegion" size="small" round>重置</el-button></el-col>
					</el-row>
				</el-form-item>
				<el-form-item label="实施时间" prop="implementTime">
					<el-date-picker
				      v-model="editForm.implementTime"
				      type="date"
				      placeholder="选择日期时间" 
				      value-format="yyyy-MM-dd" clearable>
				    </el-date-picker>
				</el-form-item>
				<el-form-item label="实施人" prop="implementUserName">
					<el-autocomplete class="inline-input" v-model="editForm.implementUserName" :fetch-suggestions="implementUserSearch"
				      placeholder="请输入实施人姓名" @select="implementUserEditSelect" size="80" clearable></el-autocomplete>
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
				<el-form-item label="项目名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"  clearable ></el-input>
				</el-form-item>
				<el-form-item label="项目类别" prop="projectTypeName">
					<el-autocomplete class="inline-input" v-model="addForm.projectTypeName" :fetch-suggestions="projectTypeSearch"
				      placeholder="请输入项目类别" @select="projectTypeAddSelect" size="80" clearable></el-autocomplete>
				</el-form-item>
				<el-form-item label="客户" prop="customerName">
					<el-autocomplete class="inline-input" v-model="addForm.customerName" :fetch-suggestions="customerSearch"
				      placeholder="请输入客户姓名" @select="customerAddSelect" size="80" clearable></el-autocomplete>
				</el-form-item>
				<el-form-item label="线路信息" prop="lineInfo">
					<el-input  v-model="addForm.lineInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="硬件信息" prop="hardwareInfo">
					<el-input  v-model="addForm.hardwareInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="软件信息" prop="softwareInfo">
					<el-input  v-model="addForm.softwareInfo"  clearable></el-input>
				</el-form-item>
				<el-form-item label="项目实施地址" prop="address">
					<el-input  v-model="addForm.address"  clearable></el-input>
				</el-form-item>
				<el-form-item label="选择地区" >
					<el-row :gutter="1">
					<el-col :span="20"><v-distpicker :province="addForm.province" :city="addForm.city" :area="addForm.area" 
						@province="provinceAdd" @city="cityAdd" @area="areaAdd" ></v-distpicker></el-col>
					<el-col :span="3"><el-button type="warning" @click="resetRegion" size="small" round>重置</el-button></el-col>
					</el-row>
				</el-form-item>
				<el-form-item label="实施时间" prop="implementTime">
					<el-date-picker
				      v-model="addForm.implementTime"
				      type="date"
				      placeholder="选择日期时间" 
				      value-format="yyyy-MM-dd"  clearable >
				    </el-date-picker>
				</el-form-item>
				<el-form-item label="实施人" prop="implementUserName">
					<el-autocomplete class="inline-input" v-model="addForm.implementUserName" :fetch-suggestions="implementUserSearch"
				      placeholder="请输入实施人姓名" @select="implementUserAddSelect" size="80" clearable></el-autocomplete>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
		<!--项目文件页面-->
		<el-dialog width="70%" :title="fileDetailsForm.name" :visible.sync="fileDetailsFormVisible">
			<el-upload
			  :action="fileAction"
			  list-type="picture-card"
			  :on-preview="handlePreview"
			  :on-remove="handleRemove"
			  :on-success="fileSuccess"
			  :data="fileDetailsForm">
			  <i class="el-icon-plus"></i>
			</el-upload>
		    <el-table :data="fileDetailsForm.files" border @cell-dblclick="cellDblclick">
			    <el-table-column prop="explain" label="说明" width="300">
			    	<template slot-scope="scope">
			           <el-input v-model="scope.row.explain" placeholder="请输入说明">
			           	<el-button slot="append" icon="el-icon-upload" @click="editExplain(scope.$index, scope.row)"></el-button>
			           </el-input>
			       </template>
			    </el-table-column>
			    <el-table-column prop="createTime" label="创建日期" width="180" :formatter="dateFormat" ></el-table-column>
			    <el-table-column prop="fileSize" label="大小(KB)" width="100"  ></el-table-column>
			    <el-table-column prop="path" label="路径" ></el-table-column>
			    <el-table-column label="操作" width="200" fixed="right"> 
					<template scope="scope">
						<el-button type="info" size="small" @click="fileDownload(scope.$index, scope.row)" icon="el-icon-download">下载</el-button>
						<el-button type="danger" size="small" @click="fileDel(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>
					</template>
				</el-table-column>
		    </el-table>
		    <div slot="footer" class="dialog-footer">
				<el-button @click.native="fileDetailsFormVisible = false">关闭</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	//import moment from 'moment';
	import { dateFormat, dateFormatSimp, dateFormats, dateFormatSimps } from '@/utils/dateformat'
	import VDistpicker from 'v-distpicker'; //省市区级联组件
	//import NProgress from 'nprogress'
	import { getProjectOne, getProjectListPage, removeProject, batchRemoveProject, editProject, addProject } from '@/api/projectApi';
	import { getCustomerNameList } from '@/api/customerApi';
	import { getProjectTypeNameList } from '@/api/projectTypeApi';
	import { getProjectFileListPage, editProjectFile, removeProjectFile, getProjectFile } from '@/api/projectFileApi';
	import { getImplementUserNameList } from '@/api/userApi';
	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				projects: [],//项目列表数据
				total: 0,
				pageNum: 1,//当前页码
				pageSize: 20,
				listLoading: false,
				sels: [],//列表选中列
				customers: [],//客户数据
				projectTypes: [],//项目类别数据
				implementUsers: [],//实施人-->用户数据
				fileList: [],//文件集合
				fileAction : '',
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
					name: '',
					projectTypeId: 0,
					projectTypeName: '',
					customerId: 0,
					customerName: '',
					lineInfo: '',
					hardwareInfo: '',
					softwareInfo: '',
					address: '',
					province: '',
	                city: '',
	                area: '',
					implementTime: '',
					implementUserId: 0,
					implementUserName: ''
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
					projectTypeId: 0,
					projectTypeName: '',
					customerId: 0,
					customerName: '',
					lineInfo: '',
					hardwareInfo: '',
					softwareInfo: '',
					address: '',
					province: '',
	                city: '',
	                area: '',
					implementTime: '',
					implementUserId: 0,
					implementUserName: ''
				},
				fileDetailsFormVisible: false,//项目类型页是否展示
				fileDetailsInsertVisible: false,//新增项目文件页是否展示
				fileDetailsForm: {//项目文件数据相关
					id: 0,
					name: '',
					files: [],
					projectId: 0,
					projectName: '',
					implementTime: '',
				},
			}
		},
		methods: {
			//性别显示转换
			formatSex: function (row, column) {
				return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
			},
			dateFormat: function (row, column) {
				return dateFormat(row, column);
			},
			dateFormatSimp: function (row, column) {
				return dateFormatSimp(row, column);
			},
			handleCurrentChange(val) {
				this.pageNum = val;//页码
				this.getProject();//翻页操作
			},
			handleSizeChange(val) {
				this.pageSize = val;//设置每页显示多少条数据
		        this.search();//重新查询
		    },
		    search() {//重新查询，并从第一页开始
		    	this.pageNum = 1;
		    	this.getProjects();
		    },
			//获取项目列表
			getProjects() {
				let para = {
					pageNum: this.pageNum,
					pageSize: this.pageSize,
					name: this.filters.name
				};
				this.listLoading = true;
				//NProgress.start();
				getProjectListPage(para).then((res) => {
					this.total = res.count;
					this.projects = res.data;
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
					removeProject(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
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
						this.getProjects();
					});
				}).catch(() => {

				});
			},
				//显示编辑界面
			handleEdit: function (index, row) {
				this.resetForm('editForm');//打开页面之前先重置表单
				this.editVisible = true;
				this.getCacheDate();
				let para = {
					id: row.id
				}
				getProjectOne(para).then((res) => {
					if (res.data.implementTime != null) {
						this.editForm.implementTime = dateFormatSimps(res.data.implementTime);
					}
					this.editForm.id = res.data.id;
					this.editForm.name = res.data.name;
					this.editForm.projectTypeId = res.data.projectTypeId;
					this.editForm.projectTypeName = res.data.projectTypeName;
					this.editForm.customerId = res.data.customerId;
					this.editForm.customerName = res.data.customerName;
					this.editForm.lineInfo = res.data.lineInfo;
					this.editForm.hardwareInfo = res.data.hardwareInfo;
					this.editForm.softwareInfo = res.data.softwareInfo;
					this.editForm.address = res.data.address;
					this.editForm.province = res.data.province;
					this.editForm.city = res.data.city;
					this.editForm.area = res.data.area;
					this.editForm.implementUserId = res.data.implementUserId;
					this.editForm.implementUserName = res.data.implementUserName;
				});
			},
			//显示新增界面
			handleAdd: function () {
				this.resetForm('addForm');
				this.addFormVisible = true;
				this.getCacheDate();
			},
			//重置表单
			resetForm: function(formName) {
				if(this.$refs[formName] !== undefined){
					this.$refs[formName].resetFields();
					this.addForm.province = '';
					this.addForm.city = '';
					this.addForm.area = '';
				}
		     },
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							if (this.editForm.projectTypeId === 0) {
								this.editForm.projectTypeId = null;
							}
							if (this.editForm.customerId === 0) {
								this.editForm.customerId = null;
							}
							if (this.editForm.implementUserId ===0) {
								this.editForm.implementUserId = null;
							}
							let para = Object.assign({}, this.editForm);
							editProject(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
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
								this.getProjects();
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
							if (this.addForm.projectTypeId === 0) {
								this.addForm.projectTypeId = null;
							}
							if (this.addForm.customerId === 0) {
								this.addForm.customerId = null;
							}
							if (this.addForm.implementUserId === 0) {
								this.addForm.implementUserId = null;
							}
							let para = Object.assign({}, this.addForm);
							addProject(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
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
								this.getProjects();
							}).catch((res) => {
								this.addLoading = false;
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
					batchRemoveProject(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						if (res.state === 'success') {
							this.$message({
								message: res.message,
								type: 'success'
							});
							this.getProjects();
						} else {
							this.$message({
								message: res.message,
								type: 'error'
							});
						}
					});
				}).catch(() => {

				});
			},
			//双击进入项目文件详情页
			handleFileDetails: function (row, event) {
				this.fileDetailsFormVisible = true;
				this.fileDetailsForm.id = row.id;
				this.fileDetailsForm.projectId = row.id;
				this.fileDetailsForm.name = row.name;
				this.fileDetailsForm.projectName = row.name;
				this.fileDetailsForm.implementTime = row.implementTime;
				this.fileAction = '/api/file/project'; //初始化请求地址
				let para = {
					projectId: row.id,
				};
				getProjectFileListPage(para).then((res) => {
					this.fileDetailsForm.files = res.data;
				});
			},
			//处理省市区三级联动
			provinceAdd: function (data) {
				this.addForm.province = data.value;
			},
			cityAdd: function (data) {
				this.addForm.city = data.value;
			},
			areaAdd: function (data) {
				this.addForm.area = data.value;
			},
			provinceEdit: function (data) {
				this.editForm.province = data.value;
			},
			cityEdit: function (data) {
				this.editForm.city = data.value;
			},
			areaEdit: function (data) {
				this.editForm.area = data.value;
			},
			//重置省市区三级联动
			resetRegion: function () {
				this.addForm.province = '';
				this.addForm.city = '';
				this.addForm.area = '';
				this.editForm.province = '';
				this.editForm.city = '';
				this.editForm.area = '';
			},
			//初始化一些数据，目前放置在 打开新增和编辑页面时调用
			getCacheDate: function () {
				//初始化客户数据
				let para = {};
				getCustomerNameList(para).then((res) => {
					this.customers =  res.data;
				});
				getProjectTypeNameList(para).then((res) => {
					this.projectTypes =  res.data;
				});
				getImplementUserNameList(para).then((res) => {
					this.implementUsers =  res.data;
				});
			},
			//客户数据相关
			customerSearch: function (queryString, cb) {
		        let customers = this.customers;
		        let results = queryString ? customers.filter(this.createFilter(queryString)) : customers;
		        // 调用 callback 返回建议列表的数据
		        cb(results);
		    },
		    //从数组中检索数据
		    createFilter: function (queryString) {
		        return (res) => {
		          return (res.value.toLowerCase().indexOf(queryString.toLowerCase()) >= 0);
		        };
		    },
		    customerAddSelect: function (item) {
		    	//console.log(item);
		    	this.addForm.customerId = item.id;
		    },
		    customerEditSelect: function (item) {
		    	this.editForm.customerId = item.id;
		    },
		    //项目类别相关
		    projectTypeSearch: function (queryString, cb) {
		        let projectTypes = this.projectTypes;
		        let results = queryString ? projectTypes.filter(this.createFilter(queryString)) : projectTypes;
		        cb(results);
		    },
		    projectTypeAddSelect: function (item) {
		    	this.addForm.projectTypeId = item.id;
		    },
		    projectTypeEditSelect: function (item) {
		    	this.editForm.projectTypeId = item.id;
		    },
		    //实施人相关
		    implementUserSearch: function (queryString, cb) {
		        let implementUsers = this.implementUsers;
		        let results = queryString ? implementUsers.filter(this.createFilter(queryString)) : implementUsers;
		        cb(results);
		    },
		    implementUserAddSelect: function (item) {
		    	this.addForm.implementUserId = item.id;
		    },
		    implementUserEditSelect: function (item) {
		    	this.editForm.implementUserId = item.id;
		    },
		    //项目文件页对上传文件进行操作
		    handleRemove: function (file, fileList) {
		        console.log(file, fileList);
		    },
		    handlePreview: function (file) {
		        console.log(file);
		    },
		    //文件上传完成后重新查询列表
		    fileSuccess: function (response, file, fileList) {
				let para = {
					projectId: this.fileDetailsForm.projectId,
				};
				getProjectFileListPage(para).then((res) => {
					this.fileDetailsForm.files = res.data;
				});
		    },
		    cellDblclick: function (row, column, cell, event) {
//		    	console.log(this.explainCollapsed );
//		    	this.explainCollapsed = !this.explainCollapsed;
//		    	console.log(this.explainCollapsed );
//		    	this.explainlinshi = row.explain;
//		 		if (column.label === "说明") {
//			        event.target.innerHTML = "";
//			        let cellInput = document.createElement("input");
//			        cellInput.value = "";
//			        cellInput.setAttribute("type", "text");
//			        cellInput.style.width = "80%";
//			        cell.appendChild(cellInput);
//			        cellInput.onblur = function() {
//			          cell.removeChild(cellInput);
//			          event.target.innerHTML = cellInput.value;
//			        };
//		    	};
		    },
		    //项目文件页修改并提交项目说明
		    editExplain: function (index, row) {
		    	if (row.explain === undefined || row.explain === '' || row.explain == null) {
		    		this.$message.error('请输入文件说明');
		    		return false;
		    	}
		    	this.$confirm('确认提交当前项目说明吗？', '提示', {}).then(() => {
					let para = {
						id: row.id,
						explain: row.explain,
					}
					editProjectFile(para).then((res) => {
						if (res.state === 'error') {
							this.$message({
								message: res.message,
								type: 'error'
							});
						} else {
							this.$message({
								message: res.message,
								type: 'success'
							});
						}
					}).catch((res) => {
						this.$message.error('出错了');
					});
				});
		    },
		    //下载文件
		    fileDownload: function(index, row) {
		    	let para = {
						id: row.id,
						path: row.path,
					}
		    	location.href='/api/projectFile/downFile?id='+row.id+'&path='+row.path;
//					getProjectFile(para).then((res) => {
//						if (res.state === 'success') {
//							this.$message({
//								message: res.message,
//								type: 'success'
//							});
//						} else {
//							this.$message({
//								message: res.message,
//								type: 'error'
//							});
//						}
//					}).catch((res) => {
//						this.$message.error('出错了');
//					});
		    },
			fileDel: function (index, row) {
				this.$confirm('确认要删除此条信息吗？', '提示', {}).then(() => {
					let para = {
						id: row.id,
					}
					removeProjectFile(para).then((res) => {
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
					}).catch((res) => {
						this.$message.error('出错了');
					});
				});
			}
		},
		mounted() {
			this.getProjects();
		},
	    components: { 
	 		VDistpicker 
	    }
	}

</script>

<style scoped>

</style>