import { Container } from "react-bootstrap";
import { Route } from "react-router";
import Footer from "./component/Footer";
import Home from "./page/Home";
import Join from "./component/Join";
import Login from "./component/Login";

function App() {
    return (
        <div>
            <Container fluid>
                <Route path="/" exact={true} component={Home}/>
                <Route path="/join" exact={true} component={Join}/>
                <Route path="/login" exact={true} component={Login}/>
                <Footer/>
            </Container>
        </div>
    );
}

export default App;
