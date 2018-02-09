<template>
	<!--项目类型页面-->
		<el-dialog title="项目文件" :visible.sync="fileDetailsFormVisible">
			<p style="color: blue">项目名称: {{ fileDetailsForm.name }}</p>
				
			<el-upload
			  :action="fileAction"
			  list-type="picture-card"
			  :on-preview="handlePreview"
			  :on-remove="handleRemove">
			  <i class="el-icon-plus"></i>
			</el-upload>
			
		  <el-table :data="fileDetailsForm.files">
		    <el-table-column property="date" label="日期" width="150"></el-table-column>
		    <el-table-column property="name" label="姓名" width="200"></el-table-column>
		    <el-table-column property="address" label="下载">
		    </el-table-column>
		    <div slot="footer" class="dialog-footer">
				<el-button @click.native="fileDetailsFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="fileDetailsSubmit" :loading="fileDetailsLoading">提交</el-button>
			</div>
		  </el-table>
		</el-dialog>
</template>

<script>
</script>

<style>
</style>