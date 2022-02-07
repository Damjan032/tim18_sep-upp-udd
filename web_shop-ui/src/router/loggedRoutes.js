import Home from "@/pages/Home";
import {basketPath, homePath, successPath} from "@/utils/paths";
import Basket from "@/pages/Basket";
import SuccessPage from "@/pages/SuccessPage";

const loggedRoutes = [
    {
        path: homePath,
        component: Home,
    },
    {
        path: basketPath,
        component: Basket,
    },
    {
        path: successPath,
        component: SuccessPage
    }
];

export default loggedRoutes;
