import React, {useState} from 'react';
import "../css/loginPage.css"
import {Link} from "react-router-dom";
const LoginPage = (props) => {
    // const dispatcher = useDispatch();
    // const { isLogin, member } = useSelector((store) => store);
    const [member, setMember]  = useState({

    });
    const [tryMember, setTryMember] = useState({
        email: "",
        password: "",
    });
    const submitLogin = (e) => {
        console.log(tryMember);
        e.preventDefault();
        fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(tryMember),
        })
            .then((res) => {
                console.log(res.headers);
                let accessToken = res.headers.get("Authorization");
                let refreshToken = res.headers.get("Refresh_Token");
                localStorage.setItem("ACCESS_TOKEN", accessToken);
                localStorage.setItem("REFRESH_TOKEN", refreshToken);
                // return res.json();
            });
    };

    const changeValue = (e) => {
        setTryMember({
            ...tryMember,
            [e.target.name]: e.target.value,
        });
    };

    return (
        <div className={"login-body"}>
            <div className={"login-body-div"}>
                <div className={"login-body-div2"}>
                    <div className={"loginPage-container"}>
                        <p className={"heading"} style={{color:"white"}}>Login in</p>
                        <div className={"box"}>
                            <p style={{color:"white"}}>email</p>
                            <div>
                                <input className={"login-input"}
                                       type={"text"}
                                       name={"email"}
                                       id={""}
                                       placeholder={"Enter your email"}
                                       onChange={changeValue}/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div className={"box"}>
                            <p style={{color:"white"}}>Password</p>
                            <div>
                                <input className={"login-input"}
                                       type={"password"}
                                       name={"password"}
                                       placeholder={"Enter your password"}
                                       onChange={changeValue}/>
                            </div>
                        </div>
                        <button className={"loginButton"} style={{color:"white", backgroundColor:"#6a9ddc"}} onClick={submitLogin}>
                            Login
                        </button>
                        <p className={"text"} style={{color:"white"}}>Dont you have account?
                            <Link to={"/join"}>Sign up</Link></p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
