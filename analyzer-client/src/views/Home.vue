<template>
<div class="app-container">
     <nav-menu />
     <section class="app-main">
        <transition name="fade-transform" mode="out-in">
            <router-view/>
        </transition>
    </section>
</div>
</template>

<script>
import NavMenu from '@/components/NavMenu.vue'
export default {
  name: 'Home',  
  components: {
    NavMenu
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
