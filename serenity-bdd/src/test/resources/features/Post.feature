Feature: Post

  @Smoke
#  @debug
  @Regression
  Scenario: Create a post
    Given User is logged in the web
    When I create post with with title "petcare", description "dhaaksakl" and status "Hapur"
    Then Post should be created with title "petcare", description "dhaaksakl" and status "Hapur"

  @Regression
  @debug
  Scenario: Update a post
    Given User is on the users page
    When The user updates post with id 17
    Then They should see the updated post

  @Regression
#  @debug
  Scenario: Delete a post
    Given User is on the users page
    When The user deletes post with id 17
    Then The post should be deleted