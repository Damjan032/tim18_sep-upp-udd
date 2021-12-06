import {homePath, basketPath} from "./paths";

const basketItem = {
    icon: 'mdi-basket',
    label: 'Basket',
    path: basketPath,
};



const homeItem = {
    icon: 'mdi-home',
    label: 'Shop',
    path: homePath,
};



class SuperAdmin {
    static name = 'SuperAdmin';
    static code = 'superAdmin';
    constructor() {
        this.items = [
            homeItem,
            basketItem,
        ]
    }
}

export {SuperAdmin};
