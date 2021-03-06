package com.shopbike.service.size;

import com.shopbike.model.Size;
import com.shopbike.repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeService implements ISizeService{
    @Autowired
    private ISizeRepository sizeRepository;

    @Override
    public Iterable<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void remove(Long id) {
        sizeRepository.deleteById(id);
    }
}
