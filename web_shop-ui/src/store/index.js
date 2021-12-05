import Vue from 'vue'
import Vuex from 'vuex'
import rules from './modules/validationRules'
import auth from './modules/auth';
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
        auth: auth,
        rules: rules,
    }
})
