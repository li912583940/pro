<template>
  <div>
    <h1>vuex 测试</h1>
    Clicked: {{ getCount }} times
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <br />
    <button @click="axiosget">发送get</button>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'

	import axios from 'axios'
  import MockAdapter from 'axios-mock-adapter';
  export default {
  	
    computed: {
  	// 使用对象展开运算符将 getters 混入 computed 对象中
    ...mapGetters([
      'getCount'
      // ...
    ])
    },
    methods: {
    ...mapActions([
      'increment', // 映射 this.increment() 为 this.$store.dispatch('increment')
      'decrement'
    ]),
      //...mapActions({
      //  add: 'increment' // 映射 this.add() 为 this.$store.dispatch('increment')
      //})
      axiosget: function () {
//    var instance = axios.create({
//			    headers: {'content-type': 'application/x-www-form-urlencoded'}
//			});
    	  axios.get('http://localhost:9000/user/findList')
    	  .then((res) =>{console.log(res.data)})
    	  //.catch((error) =>{console.log(error)})
    	}
    }
  }

</script>