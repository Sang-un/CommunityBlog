import {Route} from "react-router-dom";
import LoginPage from "./page/LoginPage";

function App() {
    return (
        <div>
            <Route path="/" exact={true} component={LoginPage}/>
        </div>
    );
}

export default App;
