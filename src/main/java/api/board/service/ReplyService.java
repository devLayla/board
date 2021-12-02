package api.board.service;

import api.board.domain.INTEREST;
import api.board.domain.Reply;
import api.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long save(Reply reply){
        Reply save = replyRepository.save(reply);
        return save.getId();
    }

    public Reply findOne(Long id){
        return replyRepository.findById(id).get();
    }

    public List<Reply> findAll(){
        return replyRepository.findAll();
    }

    public void changeInterest(Long replyId, INTEREST interest){
        Reply findReply = findOne(replyId);

        if(interest == INTEREST.NONE)
            return;

        if(interest == INTEREST.INTEREST){
            findReply.addInterest(interest);
        }

        findReply.addBoring(interest);
    }


}
