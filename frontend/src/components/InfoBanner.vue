<template>
  <div class="info__banner">
    <div class="container">
      <div class="banner__list" >
        <BannerItem v-for="i in (1, 2, 3,4, 5, 6)" :key="{i}"/>
      </div>
    </div>
  </div>
</template>

<script>
import BannerItem from "@/components/BannerItem";
export default {
  name: "InfoBanner",
  components: {BannerItem}
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.banner__list {
  display: grid;
  column-gap: 30px;
  row-gap: 30px;
  grid-template-columns: repeat(2, 1fr);
}
@media (max-width: 600px){
  .banner__list {
    display:block;
  }
}

</style>