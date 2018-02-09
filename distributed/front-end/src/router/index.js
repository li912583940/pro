import Vue from 'vue';
import VueRouter from 'vue-router';
import routes from './router.js'

Vue.use(VueRouter);

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
	//NProgress.start();
	if (to.path == '/login') {
	    sessionStorage.removeItem('user');
	}
	let user = JSON.parse(sessionStorage.getItem('user'));
	if (!user && to.path != '/login') {
	    next({ path: '/login' })
	} else {
	    next()
	}
})

//router.afterEach(transition => {
//NProgress.done();
//});

export default router;