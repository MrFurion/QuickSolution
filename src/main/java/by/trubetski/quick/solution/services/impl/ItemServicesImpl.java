package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.services.ItemServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemServicesImpl implements ItemServices {

}
