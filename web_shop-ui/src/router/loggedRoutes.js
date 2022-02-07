import Home from "@/pages/Home";
import {basketPath, homePath, successPath, failPath} from "@/utils/paths";
import Basket from "@/pages/Basket";
import SuccessPage from "@/pages/SuccessPage";
import FailPage from "@/pages/FailPage";

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
    },
    {
        path: failPath,
        component: FailPage
    }
];

export default loggedRoutes;
