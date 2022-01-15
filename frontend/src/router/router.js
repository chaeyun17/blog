import Vue from 'vue'
import Router from "vue-router";

Vue.use(Router)

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../components/posts/PostList.vue")
  },
  {
    path: "/posts",
    name: "post-list",
    component: () => import("../components/posts/PostList.vue")
  },
  {
    path: "/posts/create",
    name: "post-create",
    component: () => import("../components/posts/PostCreate.vue")
  },
  {
    path: "/posts/:id",
    name: "post-detail",
    component: () => import("../components/posts/PostDetail.vue")
  },
  {
    path: "/posts/update/:id",
    name: "post-update",
    component: () => import("../components/posts/PostUpdate.vue")
  },
]

const router = new Router({
  mode: "history",
  routes: routes
})


export default router;