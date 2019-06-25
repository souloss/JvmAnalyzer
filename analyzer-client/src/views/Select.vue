<template>
  <div class="about">
    <h1>This is an about page
      {{x}},{{y}}
    </h1>
  </div>
</template>
<script>
export default {
  data() {
return {
x: 0,
y: 0
}
},
mounted() {
window.addEventListener('mousemove', this.update)
},
destroyed() {
window.removeEventListener('mousemove', this.update)
},
methods: {
update(e) {
this.x = e.pageX
this.y = e.pageY
}
}
}
</script>