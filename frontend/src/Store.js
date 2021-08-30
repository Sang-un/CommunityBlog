const LOGIN = "LOGIN";
const LOGOUT = "LOGOUT";

export const memberLogin = (member) => {
    return {
        type: LOGIN,
        payload: member,
    };
};

export const memberLogout = () => {
    return {
        type: LOGOUT,
    };
};

const initstate = {
    isLogin: false,
    member: {
        id: "",
        name: "",
        loginId: "",
        email: "",
        role: "",
        city: "",
        street: "",
        zipcode: "",
    },
};

const reducer = (state = initstate, action) => {
    switch (action.type) {
        case LOGIN:
            return { isLogin: true, member: action.payload };
        case LOGOUT:
            return {
                isLogin: false,
                member: {
                    id: "",
                    name: "",
                    loginId: "",
                    email: "",
                    role: "",
                    city: "",
                    street: "",
                    zipcode: "",
                },
            };
        default:
            return state;
    }
};

export default reducer;
