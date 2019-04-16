import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysBanner } from 'app/shared/model/sys-banner.model';
import { SysBannerService } from './sys-banner.service';

@Component({
    selector: 'jhi-sys-banner-delete-dialog',
    templateUrl: './sys-banner-delete-dialog.component.html'
})
export class SysBannerDeleteDialogComponent {
    sysBanner: ISysBanner;

    constructor(
        protected sysBannerService: SysBannerService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysBannerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysBannerListModification',
                content: 'Deleted an sysBanner'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-banner-delete-popup',
    template: ''
})
export class SysBannerDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysBanner }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysBannerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sysBanner = sysBanner;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
