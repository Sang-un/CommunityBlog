import React from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <div>
            <>
                <Navbar bg="dark" variant="dark">
                    <Container>
                        <Link to="/" className="navbar-brand">
                            HOME
                        </Link>
                        <Nav className="me-auto">
                            <Link to="/join" className="nav-link">
                                JOIN
                            </Link>
                            <Link to="/login" className="nav-link">
                                LOGIN
                            </Link>
                        </Nav>
                    </Container>
                </Navbar>
                <br />
            </>
        </div>
    );
};

export default Header;