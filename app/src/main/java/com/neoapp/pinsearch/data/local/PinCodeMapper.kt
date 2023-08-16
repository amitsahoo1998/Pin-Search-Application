package com.neoapp.pinsearch.data.local

import com.neoapp.pinsearch.data.model.PostOffice

fun PostOffice.toPinCodeEntity() : PinCodeEntity {
    return PinCodeEntity(block = block, branchType = branchType, circle = circle, country = country, deliveryStatus = deliveryStatus,
        district = district, division = division, name = name, pinCode = pinCode, region = region, state = state)
}