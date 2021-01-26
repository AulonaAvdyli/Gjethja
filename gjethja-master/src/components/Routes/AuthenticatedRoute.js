import React from "react";
import { Route, Redirect } from "react-router-dom";

const AuthenticatedRoute = ({ path, component, token }) => (
    token ?
        <Route path={path} component={component} />
            : <Redirect to="/login" />
)

export default AuthenticatedRoute;