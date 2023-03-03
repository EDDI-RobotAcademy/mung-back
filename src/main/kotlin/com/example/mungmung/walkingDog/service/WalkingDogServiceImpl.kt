package com.example.mungmung.walkingDog.service

import com.example.mungmung.member.entity.Member
import com.example.mungmung.member.repository.MemberRepository
import com.example.mungmung.walkingDog.entity.WalkingDog
import com.example.mungmung.walkingDog.entity.WalkingDogInfo
import com.example.mungmung.walkingDog.repository.WalkingDogRepository
import com.example.mungmung.walkingDog.request.WalkingDogRegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


@Service
class WalkingDogServiceImpl : WalkingDogService {

    @Autowired
    private lateinit var walkingDogRepository: WalkingDogRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    override fun register(images: List<MultipartFile>, request: WalkingDogRegisterRequest): Boolean {
        val maybeMember: Optional<Member> = memberRepository.findById(request.memberId!!)

        if (maybeMember.isPresent) {
            var member: Member = maybeMember.get()

            try {
                var imageList: MutableList<String> = mutableListOf()
                val fileRandomName = UUID.randomUUID().toString()

                for (image: MultipartFile in images) {
                    val fileReName = fileRandomName + image.getOriginalFilename()
                    val writer = FileOutputStream("../mung-front/src/assets/walkingDog/imageList/$fileReName")

                    writer.write(image.bytes)
                    writer.close()

                    imageList.add(fileReName)
                }
                val walkingDogInfo = WalkingDogInfo(imageList.get(request.indexOfThumbnail!!), imageList)
                val walkingDog = WalkingDog(
                    imageList.get(request.indexOfThumbnail!!),
                    request.title,
                    request.content,
                    request.writer,
                    walkingDogInfo
                )
                walkingDogRepository.save(walkingDog)

            } catch (e: FileNotFoundException) {
                throw RuntimeException(e)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            return true
        }
        return false
    }
}

