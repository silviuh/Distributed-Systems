package com.sd.laborator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressionProductService {
    @Autowired
    private lateinit var cartesianProduct: CartesianProductService

    @Autowired
    private lateinit var union: UnionService

    fun executeOperation(A: Set<Int>, B: Set<Int>): Set<Pair<Int, Int>> {
        var result: MutableSet<Pair<Int, Int>> = mutableSetOf()
        result = union.executeOperation(
            cartesianProduct.executeOperation(A, B),
            cartesianProduct.executeOperation(B, B)
        ) as MutableSet<Pair<Int, Int>>

        return result
    }

}
