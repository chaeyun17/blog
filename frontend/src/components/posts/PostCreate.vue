<template>
  <div>
    <b-form @submit="onSubmit"  @submit.stop.prevent>
      <b-form-group class="mt-3" label="글 제목" label-for="post-title" id="title-input-grp" description="">
        <b-form-input v-model="post.title" id="post-title"></b-form-input>
      </b-form-group>
      
      <b-form-group class="mt-3" label="본문" label-for="post-content" id="content-input-grp" description="">
        <b-form-textarea
          id="post-content"
          v-model="post.content"
          placeholder="글 내용"
          rows="10"
          max-rows="100"
        ></b-form-textarea>
      </b-form-group>

      <b-button class="mt-3" type="submit" variant="primary">등록</b-button>
    </b-form>
    <div class="mt-3">
      <b-button type="button" @click="goList">뒤로가기</b-button>
    </div>
  </div>
</template>

<script>

export default{
  name: 'PostCreate',
  data() {
    return {
      post: {
        title: '',
        content: '',
        authorId: 1
      },
    }
  },
  methods: {
    onSubmit(event){
      event.preventDefault()
      this.$http.post('/posts', this.post)
      .then(res=>{
        console.log(res);
      });
      this.goList();
    },
    goList(){
      this.$router.push('/posts');
    }
  }
}
</script>

<style scoped>
  
</style>
