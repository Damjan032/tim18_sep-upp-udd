import router from "../../router/index";
import axios from "axios";
import Vue from "vue";


const state = {
    user: null,
    token: null,
    authorities: [],
    roles: [],
};

const mutations = {
    setLoggedUser(state, response) {
        state.user = response.user;
        state.authorities = response.authorities;
        state.roles = response.roles;
    },

    removeLoggedUser(state) {
        state.user = null;
        state.authorities = [];
        state.roles = [];
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
            let auth = false
            data.roles.forEach(role => {
                if ((role.name === "SUPER_ADMIN")) {
                    auth = true;
                }
            })
            if (!auth) {
                Vue.$toast.open({
                    message: 'You are not super admin',
                    type: 'error',
                })
                return;
            }
            sessionStorage.setItem("token", JSON.stringify(data.token));
            commit("setLoggedUser", data);


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
