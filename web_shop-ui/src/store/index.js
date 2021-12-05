import Vue from 'vue'
import Vuex from 'vuex'
import rules from './modules/validationRules'
import auth from './modules/auth';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        auth: auth,
        rules: rules,
    }
})
