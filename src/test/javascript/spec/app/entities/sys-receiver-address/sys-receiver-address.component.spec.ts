/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysReceiverAddressComponent } from 'app/entities/sys-receiver-address/sys-receiver-address.component';
import { SysReceiverAddressService } from 'app/entities/sys-receiver-address/sys-receiver-address.service';
import { SysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

describe('Component Tests', () => {
    describe('SysReceiverAddress Management Component', () => {
        let comp: SysReceiverAddressComponent;
        let fixture: ComponentFixture<SysReceiverAddressComponent>;
        let service: SysReceiverAddressService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReceiverAddressComponent],
                providers: []
            })
                .overrideTemplate(SysReceiverAddressComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysReceiverAddressComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysReceiverAddressService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysReceiverAddress(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysReceiverAddresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
