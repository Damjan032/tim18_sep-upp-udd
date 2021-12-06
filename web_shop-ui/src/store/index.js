import Vue from 'vue'
import Vuex from 'vuex'
import rules from './modules/validationRules'
import auth from './modules/auth';
import webShopItems from './modules/webShopItems';
import VuexPersist from 'vuex-persist';
import basket from "@/store/modules/basket";
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
        basket: basket,
        webShopItems: webShopItems,
        auth: auth,
        rules: rules,

    }
})
