import {createStore} from 'vuex'
import axios from "axios";
import AuthService from "@/services/auth.service";
import authHeader from "@/services/auth-header";

let baseUrl = 'http://localhost:8081';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? {status: true, user}
    : {status: false, user: null};

export default createStore({
    state: {
        productList: [],
        categoryList: [
            {id: 1, title: "for example"},
            {id: 2, title: "for example2"},
            {id: 3, title: "for example43"},
            {id: 4, title: "for example4"},
            {id: 5, title: "for example5"},
            {id: 6, title: "for example6"},
        ],
        feedList: [
            {id: 1, title: "for example feed"},
            {id: 2, title: "for example feed2"},
            {id: 3, title: "for example feed6"},
        ],
        categoryFeedList: [
            {id: 1, title: "for feed example"},
            {id: 2, title: "for feed example2"},
            {id: 3, title: "for example43"},
            {id: 4, title: "for example4"},
            {id: 5, title: "for example5"},
            {id: 6, title: "for example6"},
        ],
        currentFeedDetail: {id: 1, title: "bla-bla"},
        cartList: JSON.parse(localStorage.getItem("cartList")) || [],
        wishList: JSON.parse(localStorage.getItem("wishList")) || [],
        currentProductDetail: {id: 6, title: "for example", price: 1},
        specialProductsCart: [
            {id: 4, title: "for example", price: 121},
            {id: 5, title: "for example", price: 1},
            {id: 6, title: "for example", price: 1},
        ],
        orderList: null,
        curUser: initialState
    },
    getters: {
        getProductList(state) {
            console.log(state.productList)
            return state.productList;
        },
        getCategoryList(state) {
            return state.categoryList;
        },
        getCartList(state) {
            return state.cartList;
        },
        getFeedList(state) {
            return state.feedList;
        },
        getCategoryFeedList(state) {
            return state.categoryFeedList;
        },
        getCurrentFeedDetail(state) {
            return state.currentFeedDetail;
        },
        getProductCountCart: (state) => (id) => {
            for (const item of state.cartList) {
                if (item.id === id) {
                    return item.quantity;
                }
            }
            return 0;
        },
        getTotalPriceCartDiscount(state) {
            let sum = 0;
            for (const item of state.cartList) {
                if (item.discount)
                    sum += item.new_price * item.quantity;
                else
                    sum += item.price * item.quantity;
            }
            return sum;
        },
        getTotalPriceCart(state) {
            let sum = 0;
            for (const item of state.cartList) {
                sum += item.price * item.quantity;
            }
            return sum;
        },
        getWishList(state) {
            return state.wishList;
        },
        getCurrentProductDetail(state) {
            return state.currentProductDetail;
        },
        getSpecialProductsCart(state) {
            return state.specialProductsCart;
        },
        getOrderList(state) {
            return state.orderList;
        },
        getUser(state) {
            if (state.curUser.user)
                return state.curUser.user.user;
            return null;
        },
        getToken(state) {
            if (state.curUser.user)
                return state.curUser.user.token;
            return null;
        },
        getUserStatus(state) {
            return state.curUser.status;
        }
    },
    mutations: {
        setProductList(state, payload) {
            state.productList = payload;
        },
        setCategoryList(state, payload) {
            state.categoryList = payload;
        },
        setFeedList(state, payload) {
            state.feedList = payload;
        },
        setCurrentFeedDetail(state, payload) {
            state.currentFeedDetail = payload;
        },
        setCategoryFeedList(state, payload) {
            state.categoryFeedList = payload;
        },
        setCurrentProductDetail(state, payload) {
            state.currentProductDetail = payload;
        },
        incrementProductCart(state, payload) {
            state.cartList.filter(item => item.id === payload)[0].quantity += 1;
        },
        changeQuantityProductInCart(state, {payload, increase}) {
            let isAdded = false;
            for (const item of state.cartList) {
                if (item.id === payload.id) {
                    if (increase) {
                        item.quantity += 1
                    } else {
                        if (item.quantity === 1) {
                            state.cartList = state.cartList.filter(item => item.id !== payload.id);
                        } else {
                            item.quantity -= 1
                        }
                    }
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded && increase) {
                payload.quantity = 1;
                state.cartList.push(payload);
            }

            localStorage.setItem('cartList', JSON.stringify(state.cartList));
        },
        changeStateWishList(state, payload) {
            console.log(state.wishList.find((item) => item.id === payload.id));
            if (state.wishList.find((item) => item.id === payload.id)) {
                state.wishList = state.wishList.filter(item => item.id !== payload.id);
            } else {
                state.wishList.push({id: payload.id});
            }

            localStorage.setItem('wishList', JSON.stringify(state.wishList));
        },
        removeProductFromCart(state, payload) {
            state.cartList = state.cartList.filter(item => item.id !== payload.id);
            localStorage.setItem('cartList', JSON.stringify(state.cartList));
        },
        setOrderList(state, payload) {
            state.orderList = payload;
        },
        changeOrderList(state, payload) {
            state.orderList.push(payload);
        },
        setSpecialProductsCart(state, payload) {
            state.specialProductsCart = payload
        },

        //SECURITY

        loginSuccess(state, user) {
            state.curUser.status = true;
            state.curUser.user = user;
        },
        loginFailure(state) {
            state.curUser.status = false;
            state.curUser.user = null;
        },
        logout(state) {
            state.curUser.status = false;
            state.curUser.user = null;
        },
        registerSuccess(state, user) {
            state.curUser.status = true;
            state.curUser.user = user;
        },
        registerFailure(state) {
            state.curUser.status = false;
            state.curUser.user = null;
        }
    },
    actions: {
        loadProducts({commit}) {
            axios.get(baseUrl + "/product/list").then(r => {
                console.log(r);
                commit('setProductList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadCategories({commit}) {
            axios.get(baseUrl + "/category/list").then(r => {
                console.log(r);
                commit('setCategoryList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadFeeds({commit}) {
            axios.get(baseUrl + "/feed/list").then(r => {
                console.log(r);
                commit('setFeedList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadFeedDetail({commit}, {id}) {
            axios.get(baseUrl + "/feed/" + id).then(r => {
                console.log(r);
                commit('setCurrentFeedDetail', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadCategoriesFeed({commit}) {
            axios.get(baseUrl + "/feed_category/list").then(r => {
                console.log(r);
                commit('setCategoryFeedList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadProductDetail({commit}, {id}) {
            axios.get(baseUrl + "/product/" + id).then(r => {
                console.log(r);
                commit('setCurrentProductDetail', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadProductsById({commit}, {id}) {
            axios.get(baseUrl + "/product/list?category=" + id).then(r => {
                console.log(r);
                commit('setProductList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadProductsByFiltersId({commit}, {id, filters}) {

            //filterPriceLeft: 0,
            //         filterPriceRight: 0,
            //         delivery: "",
            //         discount: "",
            let filterUrl = ""
            if (filters.filterPriceLeft) {
                filterUrl += "&min_price=" + filters.filterPriceLeft;
            }
            if (filters.filterPriceRight) {
                filterUrl += "&max_price=" + filters.filterPriceRight;
            }
            if (filters.delivery) {
                filterUrl += "&delivery_period=" + filters.delivery;
            }
            if (filters.discount) {
                filterUrl += "&discount=" + filters.discount;
            }
            axios.get(baseUrl + "/product/list?category=" + id + filterUrl).then(r => {
                console.log(r);
                commit('setProductList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadSpecialProductsCart({commit}) {
            axios.get(baseUrl + "/product/list?category=1").then(r => {
                console.log(r);
                commit('setSpecialProductsCart', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadProductsByFilters({commit}, {filters}) {

            //filterPriceLeft: 0,
            //         filterPriceRight: 0,
            //         delivery: "",
            //         discount: "",
            let filterUrl = ""
            if (filters.filterPriceLeft) {
                filterUrl += "min_price=" + filters.filterPriceLeft;
            }
            if (filters.filterPriceRight) {
                filterUrl += "&max_price=" + filters.filterPriceRight;
            }
            if (filters.delivery) {
                filterUrl += "&delivery_period=" + filters.delivery;
            }
            if (filters.discount) {
                filterUrl += "&discount=" + filters.discount;
            }
            axios.get(baseUrl + "/product/list?" + filterUrl).then(r => {
                console.log(r);
                commit('setProductList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadFeedsById({commit}, {id}) {
            axios.get(baseUrl + "/feed/list?category=" + id).then(r => {
                console.log(r);
                commit('setFeedList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        loadOrderList({commit}) {
            axios.get(baseUrl + "/orders/user", {headers: authHeader()}).then(r => {
                console.log(r);
                commit('setOrderList', r.data);
            }).catch(e => {
                console.log(e);
            });
        },
        createOrder({commit}, {order}) {
            console.log(order)
            axios.post(baseUrl + "/orders/", order, {headers: authHeader()}).then(r => {
                console.log(r);
                commit('changeOrderList', r.data)
            }).catch(e => {
                console.log(e);
            });
        },

        //SECURITY

        login({commit}, user) {
            return AuthService.login(user).then(
                user => {
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({commit}) {
            AuthService.logout();
            commit('logout');
        },
        register({commit}, user) {
            return AuthService.register(user).then(
                user => {
                    commit('registerSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        }
    },
    modules: {}
})