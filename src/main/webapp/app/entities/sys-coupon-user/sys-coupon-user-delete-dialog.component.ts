import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';
import { SysCouponUserService } from './sys-coupon-user.service';

@Component({
    selector: 'jhi-sys-coupon-user-delete-dialog',
    templateUrl: './sys-coupon-user-delete-dialog.component.html'
})
export class SysCouponUserDeleteDialogComponent {
    sysCouponUser: ISysCouponUser;

    constructor(
        protected sysCouponUserService: SysCouponUserService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCouponUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCouponUserListModification',
                content: 'Deleted an sysCouponUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-coupon-user-delete-popup',
    template: ''
})
export class SysCouponUserDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCouponUserDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysCouponUser = sysCouponUser;
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
