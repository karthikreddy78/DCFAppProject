package com.musku.company.comments;//package com.dcfuser.user.service;
//
//import com.dcfuser.user.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserModelListener extends AbstractMongoEventListener {
//
//    private SequenceGeneratorService sequenceGeneratorService;
//
//    @Autowired
//    public UserModelListener(SequenceGeneratorService sequenceGeneratorService) {
//        this.sequenceGeneratorService = sequenceGeneratorService;
//    }
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent event) {
//        if (event.getSource().getId() < 1) {
//            event.getSource().setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
//        }
//    }
//}