/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponClassifyDeleteDialogComponent } from 'app/entities/sys-coupon-classify/sys-coupon-classify-delete-dialog.component';
import { SysCouponClassifyService } from 'app/entities/sys-coupon-classify/sys-coupon-classify.service';

describe('Component Tests', () => {
    describe('SysCouponClassify Management Delete Component', () => {
        let comp: SysCouponClassifyDeleteDialogComponent;
        let fixture: ComponentFixture<SysCouponClassifyDeleteDialogComponent>;
        let service: SysCouponClassifyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponClassifyDeleteDialogComponent]
            })
                .overrideTemplate(SysCouponClassifyDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponClassifyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponClassifyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
