import Vue from 'vue'
import Vuex from 'vuex'
import rules from './modules/validationRules'
import auth from './modules/auth';
import webShopItems from './modules/webShopItems';
import VuexPersist from 'vuex-persist';
Vue.use(Vuex)

const vuexPersist = new VuexPersist({
    key: 'my-app',
    storage: window.localStorage,
});

export default new Vuex.Store({
    state: {},
    plugins: [vuexPersist.plugin],
    namespaced: true,
    mutations: {},
    actions: {},
    modules: {
        webShopItems: webShopItems,
        auth: auth,
        rules: rules,

    }
})
