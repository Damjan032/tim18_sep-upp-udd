import Vue from "vue";

const state = {
    basketItems: [],
    length: 0,
};

const mutations = {
    setBasketItems(state, response) {
        state.basketItems = response;
    },
    addBasketItem(state, basketItem) {
        if(state.basketItems.filter(item => parseInt(item.id)===parseInt(basketItem.id)).length === 0) {
            state.basketItems.push(basketItem);
            state.length += 1;
            Vue.$toast.open({
                message: "Successfully added item.",
            });
        }
        else {
            Vue.$toast.open({
                message: "Item has already been added to cart.",
                type: 'warning'
            });
        }
    },
    deleteBasketItem(state, basketItem) {
        let index = state.basketItems.findIndex((c) => c.id === basketItem.id);
        state.basketItems.splice(index, 1);
        state.length -=1
    },
    updateBasketItem(state, basketItem) {
        state.basketItems = [...state.basketItem.filter((c) => c.id !== basketItem.id), basketItem];
    },
    deleteAllFromBasket(state) {
        state.basketItems = []
        state.length = 0
    },
}
const actions = {
    addBasketItemApi({ commit }, basketItem) {
        try {

            commit('addBasketItem', basketItem);

        } catch (err) {
            console.log(err);
        }
    },
    async deleteBasketItemApi({ commit }, basketItem) {
        try {
            commit('deleteBasketItem', basketItem)
            Vue.$toast.open({
                    message: "Successfully removed item.",
            });
        } catch (err) {
            console.log(err);
        }
    },
    deleteBasketAllApi({ commit }) {
        try {
            commit('deleteAllFromBasket')
        } catch (err) {
            console.log(err);
        }
    },
}
export default {
    namespaced: true,
    state,
    mutations,
    actions,
};