import React from 'react';
import Header from "../../components/Header/Header";
import Sidebar from "../../components/Sidebar/Sidebar";
import './Home.scss';
import Post from "../../components/Post/Post";
import {deletePost, getBoostedPosts, getPosts} from "../../actions/posts";
import { connect } from "react-redux";
import {CARD_OPTIONS, HomeTabs} from "../../utils/constants";
import {getProfiles} from "../../actions/profiles";
import Modal from 'react-modal';
import {loadStripe} from "@stripe/stripe-js";
const stripePromise = loadStripe('pk_test_51H1teeE9l7621wtln7yA1DXyqVAQ4Ld6FJwB2iLYNZKtmluZEZ93jeg2ycwuKRGOj71C7awnuQBN5qDYrDkodgp100Xdajq8Lw');

class Home extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentTab: HomeTabs.Posts,
            isModalOpen: false,
            modalId: null,
            selected_post: {}
        }
    }

    componentDidMount() {
        const { currentTab } = this.state;
        if(currentTab == HomeTabs.Posts) {
            this.props.getPosts();
            this.props.getBoostedPosts();
        } else {
            this.props.getProfiles();
        }
    }

    handlePostsClick = (selected_post) => {
        this.setState({ selected_post, isModalOpen: true });
    }

    switchTab = (tab) => {
        if(tab == HomeTabs.Posts) {
            this.props.getPosts();
        } else {
            this.props.getProfiles();
        }
        this.setState({ currentTab: tab });
    }

    closeModal = () => {
        this.setState({ isModalOpen: false });
    }

    openModal = (id) => {
        this.setState({ isModalOpen: true, modalId: id });
    }

    renderModalContent = () => {
        return (
            <div className="modal-content">
                <div className="fistName">
                    { this.state.selected_post.firstName }
                </div>
                <div className="lastName">
                    { this.state.selected_post.lastName }
                </div>
                <div className="phoneNumbers">
                    { this.state.selected_post.phoneNumber }
                </div>
                <div className="jobs">
                    { this.state.selected_post.jobs }
                </div>
            </div>
        )
    }

    render() {
        const { currentTab, isModalOpen } = this.state;
        return (
            <div>
                <Header/>
                <div className="home-container">

                    <Sidebar/>

                    <Modal

                        isOpen={isModalOpen}
                        onRequestClose={this.closeModal}
                        contentLabel="Payment Modal"
                        className="custom-modal"
                        overlayClassName="custom-modal-overlay">

                        { this.renderModalContent() }

                    </Modal>


                    <div className="main-content">
                        <div className="navigation">
                            <div className="navigation-link">
                                <button id="posts-button"
                                    onClick={() => this.switchTab(HomeTabs.Posts)}>
                                    Posts
                                </button>
                            </div>
                            <div className="navigation-link">
                                <button
                                    onClick={() => this.switchTab(HomeTabs.Profiles)}>
                                    Profiles
                                </button>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col">
                                {currentTab == HomeTabs.Posts && <div className="posts">
                                    {
                                        this.props.boosted_posts.map((post, index) => {
                                            return <Post
                                                id={post.id}
                                                title={post.user}
                                                status={post.status}
                                                description={post.description}

                                            />
                                        })
                                    }
                                </div>}
                            </div>
                        </div>
                        {currentTab == HomeTabs.Posts && <div className="posts">
                            {
                                this.props.posts.map((post, index) => {
                                    return <Post
                                        id={post.id}
                                        title={post.user}
                                        status={post.status}
                                        description={post.description}/>
                                })
                            }
                        </div>}
                        {currentTab == HomeTabs.Profiles && <div className="profiles">
                            {
                                this.props.profiles.map((profile, index) => {
                                    return <Post
                                        id={profile.id}
                                        title={profile.firstName + " " + profile.lastName}
                                        info={"Shërbimet: " + profile.jobs.join(", ")}
                                        description={"Nr. Tel: " + profile.phoneNumber}
                                        handleClick={() => this.handlePostsClick(profile)}
                                    />
                                })
                            }
                        </div>}
                    </div>
                </div>
            </div>
        );
    }

}

const mapStateToProps = state => {
    return {
        posts: state.posts,
        profiles: state.profiles,
        boosted_posts: state.boosted_posts
    }
}

const mapDispatchToProps = dispatch => ({
    getPosts: data => dispatch(getPosts()),
    getBoostedPosts: data => dispatch(getBoostedPosts()),
    getProfiles: data => dispatch(getProfiles()),
    deletePost: (data) => dispatch(deletePost(data)),
})

export default connect(mapStateToProps, mapDispatchToProps)(Home);
