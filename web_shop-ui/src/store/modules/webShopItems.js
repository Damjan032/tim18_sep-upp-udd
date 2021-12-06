import axios from "axios";
import Vue from "vue";

const state = {
    webShopItems : null,
};

const mutations = {
    setWebItems(state, response) {
        state.webShopItems = response;

    },
}

const actions = {
    async getWebItems({commit}) {
        console.log("getWebItems")
        try {
            const {data} = await axios.get("/api/products");
            if (data===undefined) {
                Vue.$toast.open({
                    message: 'No data',
                    type: 'error',
                    // all of other options may go here
                })
                return;
            }
            commit("setWebItems", data);
            console.log(data)
        } catch (e) {
            Vue.$toast.open({
                message: 'Server error',
                type: 'error',
                // all of other options may go here
            })
        }
    },
};
export default {
    namespaced: true,
    state,
    mutations,
    actions,
};
