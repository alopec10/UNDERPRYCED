<template>
  <div class="home">

    <div class="min-w-screen bg-purple-50  items-center justify-center px-5 py-5">
      <div class="mt-10 mb-10">
        <h1 class="text-purple-600 text-center text-8xl">STOCKX</h1>
      </div>
      <div
          class="w-full mx-auto rounded-xl bg-purple-100 shadow-lg p-12 text-gray-800 relative overflow-hidden min-w-80 max-w-4xl">
        <div class="relative mt-1">
          <input type="text" id="" placeholder="Buscar..."
                 class="h-12 uppercase text-center w-full pl-3 pr-10 py-2 border-2 border-gray-200 rounded-xl hover:border-purple-200 focus:outline-none focus:border-purple-500 transition-colors">
          <button
              class="block w-7 h-7 text-center text-xl leading-0 absolute top-2.5 right-3 text-purple-400 focus:outline-none hover:text-purple-900 transition-colors">
            <i class="fas fa-search"></i>
          </button>
        </div>
        <div class="absolute top-0 left-0 w-full h-2 flex">
          <div class="h-2 bg-gradient-to-r from-purple-700 to-purple-300 flex-1"></div>
        </div>
        <div class="absolute bottom-0 left-0 w-full h-2 flex">
          <div class="h-2 bg-gradient-to-l from-purple-700 to-purple-300 flex-1"></div>
        </div>
        <div class="absolute top-0 left-0 h-full w-2 flex">
          <div class=" bg-gradient-to-b from-purple-700 to-purple-300 flex-1"></div>
        </div>
        <div class="absolute top-0 right-0 h-full w-2 flex">
          <div class=" bg-gradient-to-t from-purple-700 to-purple-300 flex-1"></div>
        </div>
      </div>
    </div>
    <div class="xl:mx-32">
      <div class="grid gap-6 grid-cols-1 sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 mx-10 my-8">
        <BrandCard brand="YEEZY"
                   bgUrl="background-image:url('https://blog.klekt.com/wp-content/uploads/2020/04/adidas-Yeezy-Boost-350-V2-Zebra-On-Foot-800x450.jpg')"
                   logoUrl="https://1000marcas.net/wp-content/uploads/2020/02/yeezy-logo-transparent-png-1.png"
        ></BrandCard>
        <BrandCard brand="JORDAN"
                   bgUrl="background-image:url('https://www.43einhalb.com/media/232762/w/1000/h/600/n/nike-air-jordan-1-retro-high-og-gs-court-purple-575441-500-mood-2.jpg')"
                   logoUrl="https://i.pinimg.com/originals/01/39/93/0139937c2f641ab61fd020844ccfd459.png"
        ></BrandCard>
        <BrandCard brand="NIKE"
                   bgUrl="background-image:url('https://sneakers-magazine.com/wp-content/uploads/2017/12/sean-wotherspoon-x-nike-air-max-1-97-collectors-dream-aj4219-400-on-feet-facebook.jpg')"
                   logoUrl="https://logodownload.org/wp-content/uploads/2014/04/nike-logo.png"
        ></BrandCard>
        <BrandCard brand="OFF-WHITE"
                   bgUrl="background-image:url('https://www.wag1mag.com/wp-content/uploads/nike-preview-sept-2018-serena-virgil-7-wag1mag.jpg')"
                   logoUrl="https://iconape.com/wp-content/files/fw/85449/svg/off-white-logo.svg"
                   class="hidden lg:block"
        ></BrandCard>
        <BrandCard brand="ADIDAS"
                   bgUrl="background-image:url('https://i.pinimg.com/originals/1d/85/fc/1d85fca70f67b23ce1fbd8579770f9b0.jpg')"
                   logoUrl="http://assets.stickpng.com/images/580b57fcd9996e24bc43c4f2.png"
                   class="hidden"
        ></BrandCard>
      </div>


      <div class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-5 my-6 mx-10">
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>
        <SmallCard title="SUPREME CROSS BOX-LOGO HOODED SWEATSHIRT RED"
                   url="https://images.stockx.com/images/Supreme-Cross-Box-Logo-Hooded-Sweatshirt-Red.jpg?fit=clip&bg=FFFFFF&w=700&h=500&auto=compress&q=90&dpr=2&trim=color&updated_at=1607005929&fm=webp&ixlib=react-9.0.3&w=1446"
                   price=350
        ></SmallCard>
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>
        <SmallCard title="LEBRON XVIII XMAS IN LA"
                   url="https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421"
                   price=15
        ></SmallCard>

      </div>
    </div>
  </div>
</template>


<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import NavBar from '@/components/NavBar.vue'
import BrandCard from '@/components/BrandCard.vue'
import SmallCard from '@/components/SmallCard.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld,
    NavBar,
    BrandCard,
    SmallCard
  }
}
</script>
