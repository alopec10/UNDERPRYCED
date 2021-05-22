<template>
<div>
  <section class="error-container">
    <span>4</span>
    <span><span class="screen-reader-text">0</span></span>
    <span>4</span>
  </section>
  <button @click="$router.push('/')" type="button" style="font-family:'Quantico'"
          class="bg-purple-500 text-3xl p-2 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
    IR A INICIO
  </button>
</div>
</template>

<script>
export default {
  name: "Error"
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css?family=Montserrat:400,600,700');
@import url('https://fonts.googleapis.com/css?family=Catamaran:400,800');
.error-container {
  text-align: center;
  font-size: 180px;
  font-family: 'Quantico';
  font-weight: 800;
  margin: 20px 15px;
}
.error-container > span {
  display: inline-block;
  line-height: 0.7;
  position: relative;
  color: #FFB485;
}
.error-container > span {
  display: inline-block;
  position: relative;
  vertical-align: middle;
}
.error-container > span:nth-of-type(1) {
  color: #D1F2A5;
  animation: colordancing 4s infinite;
}
.error-container > span:nth-of-type(3) {
  color: #F56991;
  animation: colordancing2 4s infinite;
}
.error-container > span:nth-of-type(2) {
  width: 120px;
  height: 120px;
  border-radius: 999px;
}
.error-container > span:nth-of-type(2):before,
.error-container > span:nth-of-type(2):after {
  border-radius: 0%;
  content:"";
  position: absolute;
  top: 0; left: 0;
  width: inherit; height: inherit;
  border-radius: 999px;
  box-shadow: inset 30px 0 0 #DDD6FE,
  inset 0 30px 0 #A78BFA,
  inset -30px 0 0 #7C3AED,
  inset 0 -30px 0 #5B21B6;
  animation: shadowsdancing 4s infinite;
}
.error-container > span:nth-of-type(2):before {
  -webkit-transform: rotate(45deg);
  -moz-transform: rotate(45deg);
  transform: rotate(45deg);
}

.screen-reader-text {
  position: absolute;
  top: -9999em;
  left: -9999em;
}
@keyframes shadowsdancing {
  0% {
    box-shadow: inset 30px 0 0 #DDD6FE,
    inset 0 30px 0 #A78BFA,
    inset -30px 0 0 #7C3AED,
    inset 0 -30px 0 #5B21B6;
  }
  25% {
    box-shadow: inset 30px 0 0 #5B21B6,
    inset 0 30px 0 #DDD6FE,
    inset -30px 0 0 #A78BFA,
    inset 0 -30px 0 #7C3AED;
  }
  50% {
    box-shadow: inset 30px 0 0 #7C3AED,
    inset 0 30px 0 #5B21B6,
    inset -30px 0 0 #DDD6FE,
    inset 0 -30px 0 #A78BFA;
  }
  75% {
    box-shadow: inset 30px 0 0 #A78BFA,
    inset 0 30px 0 #7C3AED,
    inset -30px 0 0 #5B21B6,
    inset 0 -30px 0 #DDD6FE;
  }
  100% {
    box-shadow: inset 30px 0 0 #DDD6FE,
    inset 0 30px 0 #A78BFA,
    inset -30px 0 0 #7C3AED,
    inset 0 -30px 0 #5B21B6;
  }
}
@keyframes colordancing {
  0% {
    color: #6D28D9;
  }
  25% {
    color: #4C1D95;
  }
  50% {
    color: #8B5CF6;
  }
  75% {
    color: #C4B5FD;
  }
  100% {
    color: #EDE9FE;
  }
}
@keyframes colordancing2 {
  0% {
    color: #C4B5FD;
  }
  25% {
    color: #EDE9FE;
  }
  50% {
    color: #6D28D9;
  }
  75% {
    color: #4C1D95;
  }
  100% {
    color: #8B5CF6;
  }
}

/* demo stuff */
* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
body {
  background-color: #416475;
  margin-bottom: 50px;
}

h1 {
  text-align: center;
  margin: 30px 15px;
}
</style>