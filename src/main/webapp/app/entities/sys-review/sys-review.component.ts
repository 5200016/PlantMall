import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysReview } from 'app/shared/model/sys-review.model';
import { AccountService } from 'app/core';
import { SysReviewService } from './sys-review.service';

@Component({
    selector: 'jhi-sys-review',
    templateUrl: './sys-review.component.html'
})
export class SysReviewComponent implements OnInit, OnDestroy {
    sysReviews: ISysReview[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysReviewService: SysReviewService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysReviewService.query().subscribe(
            (res: HttpResponse<ISysReview[]>) => {
                this.sysReviews = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysReviews();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysReview) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysReviews() {
        this.eventSubscriber = this.eventManager.subscribe('sysReviewListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
