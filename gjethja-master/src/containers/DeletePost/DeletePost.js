import React, { Component } from 'react';
import { deletePost} from "../../actions/posts";
import { register } from '../../actions/user';
import { connect } from "react-redux";
import { Link } from 'react-router-dom';
import Post from "../../components/Post/Post";
import {posts} from "../../utils/posts";

class DeletePost extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            title: "",
            description: "",
            status: "",
        }
    }

    // componentDidMount() {
    //     this.props.getPosts();
    // }


    onDeleteClick(id) {
        this.props.deletePost(id, () => {
            this.props.history.push('/');
        });
    }

    render(){
        return(
             <li className="list-group-item" key={posts.id && this.state.post.id}>
                <button
                    className="btn btn-danger pull-xs-right"
                    onClick={() => this.onDeleteClick(posts.id)}
                >
                </button>
            </li>
          );
    }
 }

const mapStateToProps = state => {
    return {
        //
    };
}

// function mapStateToProps(state) {
//     return { posts: state.posts };
// }

const mapDispatchToProps = dispatch => ({
    deletePost: (data, id) => dispatch(deletePost(data, id)),
})

export default connect(mapStateToProps, mapDispatchToProps)(DeletePost);
