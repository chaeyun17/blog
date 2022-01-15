<template>
  <div class="mt-3">
    <div class="title">
      <h2>{{post.title}}</h2>
    </div>
    <b-row class="created">
      <b-col></b-col>
      <b-col cols="5">
        <p>작성일시: {{post.createdOn}}</p>
      </b-col>
    </b-row>
    <div class="content mt-5" >
      <p>{{post.content}}</p>
    </div>
    <hr>
    <span>
      <b-button type="button" variant="primary" @click="goEdit">수정</b-button>
    </span>
    <span style="padding-left: 10px">
      <b-button type="button" variant="danger" @click="deletePost">삭제</b-button>
    </span>
    <span style="padding-left: 10px">
      <b-button type="button" variant="" @click="goList">뒤로가기</b-button>
    </span>
  </div>
</template>

<script>
export default {
  name: 'PostDetail',
  data(){
    return {
      post: {}
    }
  },
  mounted(){
      this.getPost();
  },
  methods: {
    getPost(){
      this.$http.get(`/posts/${this.$route.params.id}`)
      .then(res=>{
        this.post = res.data;
      })
    },
    goList(){
      this.$router.push('/posts');
    },
    goEdit(){
      this.$router.push(`/posts/update/${this.post.id}`);
    },
    deletePost(){
      this.$http.delete(`/posts/${this.post.id}`)
      .then(res=>{
        console.log(res)
        this.goList();
      });
    },
  }
}
</script>

<style scoped>
.created{
  text-align: right;
}
.content{
  min-height: 10rem;
}
</style>