package LDHD.study.domain.post.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GetPostListResponse {

    private List <GetPostList> postList;

    public GetPostListResponse(List<GetPostList> getPostList){

        this.postList = getPostList;

    }
}
