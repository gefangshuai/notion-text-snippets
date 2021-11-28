package site.notion.timothypro.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.notion.timothypro.bean.Language
import site.notion.timothypro.service.SnippetsService

/**
 * @author gefangshuai
 * @createDate: 2021/10/7
 */
@RequestMapping("/snippets")
@RestController
class SnippetsController {
    @Autowired
    private lateinit var snippetsService: SnippetsService

    /**
     * @param token
     * @param parentId PageId
     * @param language Language
     */
    @PostMapping
    fun save(
        @RequestHeader token: String,
        @RequestHeader parentId: String,
        @RequestHeader(defaultValue = "zh_CN") language: Language,
        content: String
    ): ResponseEntity<String> {
        val response = snippetsService.save(data = content, token = token, parentId = parentId, language = language)
        return ResponseEntity.status(response.code).body(response.body?.string())
    }

    @GetMapping
    fun hello() = "Snippets Running!"
}