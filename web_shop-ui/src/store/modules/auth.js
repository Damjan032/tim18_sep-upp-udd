import router from "../../router/index";
import axios from "axios";
import Vue from "vue";


const state = {
    user: null,
    token: null,
    loggedIn:false,
    authorities: [],
    roles: [],
};

const mutations = {
    setLoggedUser(state, response) {
        state.user = response.email;
        state.token = response.token;
        state.authorities = response.authorities;
        state.roles = response.roles;
        state.loggedIn= true;
    },

    removeLoggedUser(state) {
        console.log("YSOSSDADADAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        state.user = null;
        state.authorities = [];
        state.roles = [];
        state.loggedIn= false;
    },
};

const actions = {
    async login({commit}, authRequest) {
        try {
            const {data} = await axios.post("/auth/login", authRequest);
            if (data.roles.length === 0) {
                Vue.$toast.open({
                    message: 'Incorrect username or password',
                    type: 'error',
                    // all of other options may go here
                })
                return;
            }
            sessionStorage.setItem("token", JSON.stringify(data.token));
            commit("setLoggedUser", data);
            console.log(data)


            router.push("/");
        } catch (e) {
            console.log("USAO OCDE")
            Vue.$toast.open({
                message: 'Incorrect username or password',
                type: 'error',
                // all of other options may go here
            })
        }
    },
    async logout({commit}) {
        await axios.post("/auth/logout", {
            token: sessionStorage
                .getItem("token")
                .substring(1, sessionStorage.getItem("token").length - 1),
        });
        sessionStorage.removeItem("token");
        commit("removeLoggedUser");


        router.push("/auth");
    },
};

const getters = {
    getUser: (state) => state.user,
    isLogged: (state) => state.user === null,
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};
