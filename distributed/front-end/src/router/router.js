import Login from '@/views/Login.vue'
import NotFound from '@/views/404.vue'
import Home from '@/views/Home.vue'
import Main from '@/views/Main.vue'
import Form from '@/views/nav1/Form.vue'
import Page4 from '@/views/nav2/Page4.vue'
import Page5 from '@/views/nav2/Page5.vue'
import Page6 from '@/views/nav3/Page6.vue'
import echarts from '@/views/charts/echarts.vue'
import userMain from '@/views/userManage/userMain.vue'
import roleMain from '@/views/userManage/roleMain.vue'

//项目管理
import projectMain from '@/views/projectManage/projectMain.vue'
import projectType from '@/views/projectManage/projectType.vue'
import projectFile from '@/views/projectManage/projectFile.vue'

let router = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '项目管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/projectMain', component: projectMain, name: '项目' },
            { path: '/projectFile', component: projectFile, name: '项目文件' },
            { path: '/projectType', component: projectType, name: '项目类别' },
            { path: '/form', component: Form, name: 'Form' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '客户管理',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
//  {
//      path: '/',
//      component: Home,
//      name: '分布测试',
//      iconCls: 'fa fa-address-card',
//      leaf: true,//只有一个节点
//      children: [
//          { path: '/page6', component: Page6, name: '分布测试' }
//      ]
//  },
    {
        path: '/',
        component: Home,
        name: '系统设置',
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    {
    	path: '/',
    	component: Home,
    	name: '用户管理',
    	iconCls: 'fa fa-address-card',
    	children: [
    		{ path: '/userManage/userMain', component: userMain, name: '用户' },
    		{ path: '/userManage/roleMain', component: roleMain, name: '角色' }
    	]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default router;