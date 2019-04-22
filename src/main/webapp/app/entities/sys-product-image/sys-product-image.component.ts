import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysProductImage } from 'app/shared/model/sys-product-image.model';
import { AccountService } from 'app/core';
import { SysProductImageService } from './sys-product-image.service';

@Component({
    selector: 'jhi-sys-product-image',
    templateUrl: './sys-product-image.component.html'
})
export class SysProductImageComponent implements OnInit, OnDestroy {
    sysProductImages: ISysProductImage[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysProductImageService: SysProductImageService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysProductImageService.query().subscribe(
            (res: HttpResponse<ISysProductImage[]>) => {
                this.sysProductImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysProductImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysProductImage) {
        return item.id;
    }

    registerChangeInSysProductImages() {
        this.eventSubscriber = this.eventManager.subscribe('sysProductImageListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
