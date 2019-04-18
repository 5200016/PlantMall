import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysRecharge } from 'app/shared/model/sys-recharge.model';
import { SysRechargeService } from './sys-recharge.service';

@Component({
    selector: 'jhi-sys-recharge-delete-dialog',
    templateUrl: './sys-recharge-delete-dialog.component.html'
})
export class SysRechargeDeleteDialogComponent {
    sysRecharge: ISysRecharge;

    constructor(
        protected sysRechargeService: SysRechargeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysRechargeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysRechargeListModification',
                content: 'Deleted an sysRecharge'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-recharge-delete-popup',
    template: ''
})
export class SysRechargeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysRecharge }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysRechargeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysRecharge = sysRecharge;
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
