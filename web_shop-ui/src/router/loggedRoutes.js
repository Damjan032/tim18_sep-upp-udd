import Home from "@/pages/Home";
import {basketPath, homePath} from "@/utils/paths";
import Basket from "@/pages/Basket";

const loggedRoutes = [
    {
        path: homePath,
        component: Home,
    },
    {
        path: basketPath,
        component: Basket,
    },
];

export default loggedRoutes;
