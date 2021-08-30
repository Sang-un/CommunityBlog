import Header from "./Header";
import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";

const Join = (props) => {

    const [member, setMember] = useState({
        name: "",
        password: "",
        email: "",
        loginId: "",
        phone: "",
    });

    const changeValue = (e) => {
        setMember({
            ...member,
            [e.target.name]: e.target.value,
        });
    };

    const submitJoinForm = (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/api/user/signup", {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8" },
            body: JSON.stringify(member),
        })
            .then((res) => {
                return res.json();
            })
            .then((res) => {
                setMember(res);
                return props.history.push("/");
            });
    };

    return (
        <div>
            <Header />
            <h1>회원가입</h1>
            <Form onSubmit={submitJoinForm}>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>이름</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="이름을 입력하세요"
                        onChange={changeValue}
                        name="name"
                    />
                </Form.Group>
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
                    <Form.Label>이메일</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="community@nav.com"
                        onChange={changeValue}
                        name="email"
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>패스워드</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="5자리 이상입력"
                        onChange={changeValue}
                        name="password"
                    />
                </Form.Group>
                <Button variant="success" type="submit">
                    SUBMIT
                </Button>
            </Form>
        </div>
    );
};

export default Join;