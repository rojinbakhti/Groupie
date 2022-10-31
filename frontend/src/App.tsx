import React, { useEffect, useContext } from "react";
import LoginPage from "./pages/LoginPage";
import { Switch, Route } from "react-router-dom";
import EditGroupDate from "./pages/EditGroupDate";
import Inviter from "./pages/Inviter";
import SettingsPage from "./pages/SettingsPage";
import SignUpPage from "./pages/SignUpPage";
import AvailabilityPage from "./pages/AvailabilityPage";
import Footer from "./components/Footer";
import ViewInvite from "./pages/ViewInvite";
import Sidebar from "./components/Sidebar";
import ActivityFeed from "./components/ActivityFeed";
import { inactivityTimer } from "./common/inactivity";
import Header from "./components/Header";
import GlobalContext from "./context/GlobalContext";

function App() {
    const { username, logout } = useContext(GlobalContext);

    useEffect(() => {
        if (username) inactivityTimer(logout!);
    }, [username]);

    return (
        <div className="App">
            <div className="content">
                <Header />
                <Switch>
                    {!username && (
                        <>
                            <Route exact path="/">
                                <LoginPage />
                            </Route>
                            <Route path="/signup">
                                <SignUpPage />
                            </Route>
                        </>
                    )}
                    {username && (
                        <>
                            <Sidebar />
                            <ActivityFeed />
                            <Route path="/settings">
                                <SettingsPage />
                            </Route>
                            <Route path="/propose">
                                <EditGroupDate />
                            </Route>
                            <Route path="/invite" component={ViewInvite} />
                            <Route path="/inviter" component={Inviter} />
                            <Route exact path="/">
                                <AvailabilityPage />
                            </Route>
                        </>
                    )}
                </Switch>
            </div>
            <Footer />
        </div>
    );
}

export default App;
