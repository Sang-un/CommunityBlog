import React, {useState} from 'react';
import Header from "./Header";
import {Button, Form} from "react-bootstrap";

const Login = (props) => {
    const [member, setMember] = useState({
        loginId:"",
        password:"",
    })

    const changeValue = (e)=>{
        setMember({
            ...member,
            [e.target.name]: e.target.value,
        });
    }

    const submitLoginForm = (e)=>{
        fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(member),
        })
            .then((res) => {
                let accessToken = res.headers.get("ACCESS_TOKEN");
                let refreshToken = res.headers.get("REFRESH_TOKEN");
                localStorage.setItem("ACCESS_TOKEN", accessToken);
                localStorage.setItem("REFRESH_TOKEN", refreshToken);
                return res.json();
            })
            .then((res) => {
                alert("로그인되었습니다.");
                props.history.push("/");
            });
    }

    return (
        <div>
            <Header />
            <h1>로그인</h1>
            <Form onSubmit={submitLoginForm}>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="아이디를 입력하세요"
                        onChange={changeValue}
                        name="loginId"
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="*******"
                        onChange={changeValue}
                        name="password"
                    />
                </Form.Group>
                <Button variant="success" type="submit">
                    LOGIN
                </Button>
            </Form>
        </div>
    );
};

export default Login;