<!-- NEW - add a route to the profile page -->
<!-- NEW - add a route to the profile page -->
<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>|

      <!-- NEW - add a route to the profile page -->
      <router-link v-if="$auth.isAuthenticated" to="/profile"
        >Profile</router-link
      >
    </div>
    <router-view />
  </div>
</template>
